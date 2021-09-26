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