
mod card {
    fn foo() {
        println!("TRACER hello from card::foo");
    }
}

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);
    }
}
