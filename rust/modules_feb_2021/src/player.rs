
pub fn foo(caller: &str) {
    let who_am_i = String::from("player:foo");

    crate::strategy::strategy::select(&who_am_i);
    crate::card::card::bar(&who_am_i);
    crate::hand::deal(&who_am_i);

    println!("TRACER player::foo from {}", caller);
}

