const store = pm.require('@wit888-2671/store');
const lodash = require('lodash');

const responseBodyAsText = pm.response.text();
const requestBodyAsText = pm.request.body.raw;

const responceBodyAsJson = pm.response.json();
const requestBodyAsJson = JSON.parse(pm.request.body.raw);


pm.test("Response body equals request body with lodash", function () {
    pm.expect(lodash.isEqual(responceBodyAsJson, requestBodyAsJson)).to.be.true;
});

pm.test("Response body equals request body with hash", function(){
    pm.expect(pm.collectionVariables.get("hashRecord")).eqls(store.hash(responseBodyAsText));
    
})