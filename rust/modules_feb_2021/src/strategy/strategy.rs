
    pub fn select(caller: &str) {
        crate::card::card::bar(&String::from("strategy:select"));
        println!("TRACER strategy::select from {}", caller);
    }

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);
    }
}
