### What are Web Components?
Web Components are components built based on collection of web standards in the HTML specification. The standards allow you to create new HTML tags with custom names that are reusable and fully encapsulate their functionality and styles. The standards are supported by all modern browsers.

#### Specifications
The Web Component’s specification consist of four main standards that can be used independently or together:

- **Custom Elements**  
  A set of APIs to define new HTML elements and their functionality.
- **Shadow DOM**  
  A set of APIs to provide encapsulation of the element’s styles markup and functions, so that the Web Component remains "hidden" and separate from the rest of the DOM.
- **ES Modules**    
  Defines standards for the inclusion and reuse of JavaScript documents in other JS documents. Enables modular Web Component development that aligns JavaScript application development standards.
- **HTML Template**  
  The <template> element allows you to input fragments of HTML that remain inert at page load, but can be instantiated at runtime.
  

The site [https://www.webcomponents.org/](https://www.webcomponents.org/) contains a lot of web components that we can bring into Vaadin Flow world


#### Integrating a Web Component with Vaadin Flow
To be able to use a Web Component in Vaadin you need:
- To load the HTML/JS/CSS files needed by the component (see instructions below).
- A Java API to configure the component and listen to events from it.

While you can start from scratch and do it all manually, the easiest way is to use the [Vaadin Add-on Starter](https://vaadin.com/start/lts/component). This gives you:
- A project with all the necessary dependencies.
- An npm import for the selected component.
- A stub component Java class for your Web Component integration.
- A Maven profile that handles everything necessary to deploy the component to Vaadin Directory.

I have built a sample with the Add-on Starter[Vaadin Flow sample addon paper slider](https://github.com/shx99717/vaadin-flow-sample-addon-paper-slider)


#### Debugging a Web Component Integration
- If you need to debug the JavaScript, for example because the Web Component does not behave as expected, or do what is intended, you can use the browser debugger to set breakpoints at suitable places.

In more problematic cases, for example if the problem occurs on initial setup, you can add a debugger; statement to the Web Component code to ensure the browser always (and automatically) breaks at a particular point. To do this, you need to edit the Web Component included in your project. All components used in the project are downloaded by npm and located in the node_modules folder, under the project root folder.

For example, to debug the increment() function in paper-slider component, you could:
1. Launch the project in developer mode to ensure that any frontend file changes are used automatically after page reload.
2. Find paper-slider in the node_modules directory: node_modules/@polymer/paper-slider.
3. Add a debugger statement to the increment: function() { function.
4. Reload the page and click the "Increment" button with the inspector window open.


#### Difference between `@JsModule` and `@JavaScript` in npm mode
Firstly, read **ES Modules, the JavaScript Module System.pdf** to understand what is a ES Module now natively supported by browser, was otherwise had to be done by CommonJS(e.g. require("")) in the past  
source: [@JSModule vs. @Javascript](https://mvysny.github.io/Vaadin-difference-jsmodule-javascript/)


Say you have a script such as

```javascript
function test(val){
    alert('Hi'+val);
}
```
and you want to call the function as UI.getCurrent().getPage().executeJs("test('User')");. You try to place the script into the frontend/ folder, then load the script via @JavaScript, and it doesn’t work. Unfortunately that’s the way things work; please read below on why is that and what can be done about it.

The reason is that the script has been loaded as module script; and since the test function was not published, it is internal in the module and nobody can access it.

The main difference is that

* `@JsModule` always loads the script as the module script
* `@JavaScript` 1.loads the script either as the module script (if the path to script is prefixed with `./` - then the script will be loaded from the `frontend/` folder)  2. as a classic script (if loading from the external URL such as `https://`).
  
The difference between the module script and the classic script is summarized as:
1. Modules are singleton. They will be loaded and executed only once.
2. Modules can use import and export.
3. Modules are always executed in strict mode.
4. All objects (class, const, function, let or var) are private unless explicitly exported.
5. The value of “this” is undefined at the outer scope (not window).
6. Modules are loaded asynchronously.
7. Modules are loaded using CORS. see Access-Control-Allow-Origin: *.
8. Modules don’t send cookies and authentication info by default. See crossorigin=”use-credentials”.
9. imports are resolved statically at load time rather than dynamically at runtime.
10. html comments are not allowed.

You should always prefer js code which loads as the module script - you can find such libraries in the npmjs repository. However, certain old scripts won’t work as module scripts (most probably because of the strict mode); then you will need to load them as old scripts.

##### So .. HowTo Loading classic scripts from your app
You currently can’t use an annotation-based approach to load a classic script locally from your app:

1. `@HtmlImport` annotation is ignored in the npm mode - it does absolutely nothing. Keep that in mind and never use this annotation unless you’re also targeting Vaadin 14 compatibility mode.
2. `@JsModule` always loads the script as a module script;
3. `@JavaScript` always loads the script as a module script when loading the script locally. You can’t trick `@JavaScript` to load the script as *external* from your WAR `src/main/webapp` using the `context://` prefix since that’s broken.
The only way to load a script as a classic script is to place the javascript file into e.g. `src/main/webapp/js/test.js` and call `Page.addJavaScript("context://js/test.js")`.

Therefore, as a wrap-up, you can use both `@JsModule` and `@JavaScript` to load script as a module script. You should always prefer `@JsModule` over `@JavaScript` when loading module scripts:

the `@JavaScript` only loads stuff from the `frontend/` folder, while @JsModule is able to load the script both from `frontend/` and `node_modules/` folder;
also, the name `@JsModule` clearly states that the script is going to be loaded as a module script.

Certain scripts won’t work as module scripts though, because of strict mode. In such case you’ll have to load them as classic scripts.

##### So ... The solution to the problem above
**Solution 1: Publishing the function to window**
You can publish the function to the window object as follows:

```javascript
window.test = function test(val){
    alert('Hi'+val);
}
```
Then the function should be callable via `UI.getCurrent().getPage().executeJs("test('User')");`, since the javascript snippet runs in the context of the `window` object.

**Solution 2: Exporting the function**
An alternative approach would be to load the script as a module script and export the test function as follows:

```javascript
export function test(val){
    alert('Hi'+val);
}
```
Unfortunately, such exported function is not callable via `UI.getCurrent().getPage().executeJs("test('User')");` because the code snippet running via `executeJs()` would have to import the module script first. That is currently not possible.