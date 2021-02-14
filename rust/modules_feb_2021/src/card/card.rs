
    pub fn bar(caller: &str) {
        println!("TRACER card::bar from {}", caller);
    }

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);
    }
}
