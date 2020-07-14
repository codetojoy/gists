
#[derive(Debug)]
struct Person {
    name: String,
}

#[derive(Debug)]
struct Table {
    people: Vec<Person>,
}

#[derive(Debug)]
struct Config {
    people: Vec<Person>,
}

fn process_table(table: Table) {
    for p in table.people {
        println!("TRACER p: {:?}", p);
    }
}

fn main() {
    let p1 = Person{name: String::from("bach")};
    let p2 = Person{name: String::from("chopin")};
    let p3 = Person{name: String::from("mozart")};
    let list = vec![p1, p2, p3];
    let table = Table{people: list};

    process_table(table);

    println!("Ready.");
}
