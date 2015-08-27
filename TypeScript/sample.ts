
class Student {
    fullname : string;
    constructor(public firstname, public middleinitial, public lastname) {
        this.fullname = firstname + " " + middleinitial + " " + lastname;
    }
}

interface Person {
    firstname: string;
    lastname: string;
}

function greeter(person : Person) {
    return "Hello 2, " + person.firstname + " " + person.lastname;
}

var user = new Student("Jane", "M.", "User");
console.log("user =" + user);
console.log("fullname="+user.fullname);

document.body.innerHTML = greeter(user);
