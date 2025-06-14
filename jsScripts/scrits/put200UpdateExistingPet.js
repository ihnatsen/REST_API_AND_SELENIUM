pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Was changed", function(){
    pm.expect(pm.response.json().name).eqls("Zusia");
})
