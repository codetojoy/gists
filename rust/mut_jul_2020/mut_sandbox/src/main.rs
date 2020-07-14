
#[derive(Debug)]
struct Person {
    name: String,
    list: Vec<u32>,
}

#[derive(Debug)]
struct Table {
    people: Vec<Person>,
}

fn process_table(table: &mut Table) {
    table.people[0].list = vec![1,2,3];
}

fn main() {
    let p1 = Person{name: String::from("bach"), list: vec![]};
    let p2 = Person{name: String::from("chopin"), list: vec![]};
    let p3 = Person{name: String::from("mozart"), list: vec![]};
    let people = vec![p1, p2, p3];
    let mut table = Table{people: people};

    process_table(&mut table);

    for p in table.people {
        println!("TRACER p: {:?}", p);
    }

    println!("Ready.");
}
