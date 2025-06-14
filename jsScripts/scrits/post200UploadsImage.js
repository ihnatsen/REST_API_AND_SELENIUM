pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("File has size  47426 bytes", function () {
    pm.expect(pm.response.json().message).contains("47426");
});