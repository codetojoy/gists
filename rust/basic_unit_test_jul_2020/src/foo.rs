
pub fn reverse(s: &str) -> String {
    let result = s.chars().rev().collect::<String>();
    result 
}

#[cfg(test)]
mod tests {
    use super::*;

	#[test]
	fn test_reverse_success() {
        let result = reverse("0515");        
		assert_eq!(result, "5150");
	}
	#[test]
	fn test_float() {
		let expected = 1f64;
		let mut actual = 1f64;
		let precision = 0.1f64;
		actual = actual + precision / 2f64;
		assert!((expected - actual).abs() < precision);
	}
	#[test]
	fn test_success() {
		assert!(1 == 1);
		assert_eq!(1, 1);
	}
}

#[cfg(not(test))]
fn main() {
	println!("This program must be build and run with --test");
}
