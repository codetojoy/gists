
mod foo;

fn main() {
    let s = foo::reverse("test");
    println!("TRACER s: {}", s);
}
