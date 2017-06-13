// Import components packaged using webpack format
// Omaha

(function (){
    console.log("Hello from webpackshim");
        var g;
        if (typeof window !== "undefined") {
          g = window;
        } else if (typeof global !== "undefined") {
          g = global;
        } else if (typeof self !== "undefined") {
          g = self;
        } else {
          // works providing we're not in "use strict";
          // needed for Java 8 Nashorn
          // see https://github.com/facebook/react/issues/3037
          g = this;
        }
        var $env = (typeof __ScalaJSEnv === "object" && __ScalaJSEnv) ? __ScalaJSEnv : {};

        console.log("ReactAce:"+ g.ReactAce);// = module.exports = ReactAce;
        g.ReactAce = g.ReactAce.default;
        //g.ReactAce.prototype.isReactComponent = {};
        //g.ReactAce = React.createFactory(g.ReactAce.default);
        //g.ReactAceFactory = g.ReactAce.default
})();