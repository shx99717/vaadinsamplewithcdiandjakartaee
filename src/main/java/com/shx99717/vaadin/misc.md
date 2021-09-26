### Development Mode vs. Production Mode
The main difference between the development and production modes is that in the development mode Vaadin uses webpack to serve JavaScript files to the browser, instead of the Java server the app is running on. This is so that if you change a JS or CSS file, your changes are picked up and served automatically. When in production mode, you do not want this extra overhead since the files will not change; it is more efficient to prepare JavaScript and CSS files once, during build, and let one server (the Java Server) serve all requests. At the same time, the client resources can be optimized and minified to reduce the load on the network and browser even further.

[HowTo enable the production mode](https://vaadin.com/docs/v14/guide/production)

### The Shipped vaadin-maven-plugin
```xml
<--
    Take care of synchronizing java dependencies and imports in
    package.json and main.js files.
    It also creates webpack.config.js if not exists yet.
-->
<plugin>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-maven-plugin</artifactId>
    <version>${vaadin.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-frontend</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
##### goal - prepare-frontend
This goal validates whether the node and npm tools are installed and not too old (node version 10 or later and npm version 5.6 or later), and also installs them automatically to the .vaadin folder in the user’s home directory if they are missing. If they are installed globally but too old, there will be an error message suggesting to install newer versions instead. Node.js is needed to run npm for installing frontend dependencies and webpack which bundles the frontend files served to client.

In addition, it visits all resources used by the application and copies them under `node_modules` folder so they are available when `webpack` builds the frontend. It also creates or updates `package.json`, `webpack.config.json` and `webpack.generated.json` files.


##### goal - build-frontend
This goal builds the frontend bundle. This is a complex process involving several steps:
- update `package.json` with all `@NpmPackage` annotation values found in the classpath and automatically install these dependencies.
- update the JavaScript files containing code for importing everything used in the application. These files are generated in the `target/frontend` folder, and will be used as entry point of the application.
- create `webpack.config.js` if not found, or updates it in case some project parameters have changed.
- generate JavaScript bundles, chunks and transpile to ES5 using `webpack` server. Target folder in case of `war` packaging is `target/${artifactId}-${version}/build` and in case of `jar` packaging is `target/classes/META-INF/resources/build`.