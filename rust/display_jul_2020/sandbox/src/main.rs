
use std::fmt;

struct Person {
    name: String,
    location: String,
    dept: String,
}

impl fmt::Display for Person {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "name: {}, location: {}, dept: {}", self.name, self.location, self.dept)
    }
}

fn main() {
    let p = Person{name: String::from("Mozart"), 
                   location: String::from("Salzburg"), 
                   dept: String::from("Music")};

    println!("TRACER {}", p);

    println!("Ready.");
}
