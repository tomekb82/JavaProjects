var Student = (function () {
    function Student(firstname, middleinitial, lastname) {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.fullname = firstname + " " + middleinitial + " " + lastname;
    }
    return Student;
})();
function greeter(person) {
    return "Hello 2, " + person.firstname + " " + person.lastname;
}
var user = new Student("Jane", "M.", "User");
console.log("user =" + user);
console.log("fullname=" + user.fullname);
document.body.innerHTML = greeter(user);
