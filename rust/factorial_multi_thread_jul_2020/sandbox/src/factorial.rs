
use std::collections::HashMap;
use std::sync::Mutex;

use primes;

pub mod util;
use util::t_log;

const MAX_SIZE: usize = 300;
const HEURISTIC_LIMIT: u64 = 14;

struct FactorialWorker {
    mutex_table: Mutex<HashMap<u64,[u64; MAX_SIZE]>>,
    mutex_factorization_worker: Mutex<FactorizationWorker>,
}

impl FactorialWorker {
    fn new() -> FactorialWorker {
        FactorialWorker {
            mutex_table: Mutex::new(HashMap::new()),
            mutex_factorization_worker: Mutex::new(FactorizationWorker::new()),
        }
    }

    fn get_factorial(&mut self, n: u64) -> [u64;  MAX_SIZE] {
        let mut table = self.mutex_table.lock().unwrap();
        if table.contains_key(&n) {
            // println!("TRACER cache hit!");
            *table.get(&n).unwrap()
        } else {
            let result = self.compute_factorial(n);
            table.insert(n, result);
            result
        }
    }

    fn compute_factorial(&self, n: u64) -> [u64;  MAX_SIZE] {
        let mut result: [u64; MAX_SIZE] = [0; MAX_SIZE];
        let mut factorization_worker = self.mutex_factorization_worker.lock().unwrap();
        for i in 0..n+1 {
            let factor = factorization_worker.get_factorization(i);
            for j in 0..MAX_SIZE {
                result[j] += factor[j];
            }
        }
        result
    }
}

struct FactorizationWorker {
    mutex: Mutex<HashMap<u64,[u64; MAX_SIZE]>>,
}

impl FactorizationWorker {
    fn new() -> FactorizationWorker {
        FactorizationWorker {
            mutex: Mutex::new(HashMap::new()),
        }
    }

    fn get_factorization(&mut self, n: u64) -> [u64;  MAX_SIZE] {
        let mut table = self.mutex.lock().unwrap();
        if table.contains_key(&n) {
            // println!("TRACER cache hit!");
            *table.get(&n).unwrap()
        } else {
            let result = self.compute_factorization(n);
            table.insert(n, result);
            result
        }
    }

    fn compute_factorization(&self, n: u64) -> [u64;  MAX_SIZE] {
       let mut result: [u64; MAX_SIZE] = [0; MAX_SIZE];
       let factors = primes::factors(n);
       for i in factors {
           // println!("TRACER g_f i {}", i);
           result[i as usize] += 1;
       }
       result
    }
}

fn my_compare(a: &[u64; MAX_SIZE], b: &[u64; MAX_SIZE], c: &[u64; MAX_SIZE]) -> bool {
    let mut result = true;

    for i in 0..MAX_SIZE {
        if a[i] + b[i] != c[i] {
            result = false;
            return result;
        }
    }

    result
}

fn my_log(a: &[u64; MAX_SIZE]) {
    let mut s = String::new();

    for i in 0..MAX_SIZE {
        if a[i] > 0 {
            s.push_str(&format!("{}^{} ", i, a[i]));
        }
    }

    t_log(&format!("\nTRACER {}\n", s));
}

#[allow(dead_code)]
fn my_log_triplet(a: &[u64; MAX_SIZE], b: &[u64; MAX_SIZE], c: &[u64; MAX_SIZE]) {
    my_log(a);
    my_log(b);
    my_log(c);
}

fn do_stop_early(a_factorial: &[u64; MAX_SIZE], b_factorial: &[u64; MAX_SIZE],
                    c_factorial: &[u64; MAX_SIZE], c: u64) -> bool {
    let mut result = false;

    if c < HEURISTIC_LIMIT {
        let a_number = get_number(a_factorial);
        let b_number = get_number(b_factorial);
        let c_number = get_number(c_factorial);
        // println!("TRACER is_greater_than {} {} {}", a_number, b_number, c_number);
        result = (a_number * b_number) > c_number
    }
    result
}

// TODO: handle the inevitable overflow panic
fn get_number(n: &[u64; MAX_SIZE]) -> u64 {
    let mut result: u64 = 1;

    // my_log(n);
    for i in 0..MAX_SIZE {
        if n[i] > 0 {
            let value = i as u64;
            result *= u64::pow(value, n[i] as u32);
        }
    }

    result
}

pub fn find_factors(c_low: u64, c_high: u64) {
    let check_frequency = 10;
    let mut count = 1;
    let mut factorial_worker = FactorialWorker::new();

    for c in c_low..c_high {
        for a in 1..c {
            for b in a..c {
                let a_factorial = factorial_worker.get_factorial(a);
                let b_factorial = factorial_worker.get_factorial(b);
                let c_factorial = factorial_worker.get_factorial(c);
                let do_check = count % check_frequency == 0;
                if do_check && do_stop_early(&a_factorial, &b_factorial, &c_factorial, c) {
                    // println!("TRACER early eject :: {}! x {}! > {}!", a, b, c);
                    break;
                } else {
                    if my_compare(&a_factorial, &b_factorial, &c_factorial) {
                        t_log(&format!("TRACER HIT :: {}! x {}! = {}!", a, b, c));
                    }
                }
                count += 1;
            }
        }
    }

    t_log(&format!("TRACER COMPLETE {} {}", c_low, c_high));
}

#[allow(unused_imports)]
mod tests {
    use super::*;

    #[test]
    fn test_get_number() {
        let mut factorial_worker = FactorialWorker::new();
        let n = 4;
        let n_factorial = factorial_worker.get_factorial(n);

        // test
        let result = get_number(&n_factorial);

        assert_eq!(result, 24);
    }

    #[test]
    fn test_do_stop_early_sanity() {
        let mut factorial_worker = FactorialWorker::new();
        let a_factorial = factorial_worker.get_factorial(6);
        let b_factorial = factorial_worker.get_factorial(7);
        let c: u64 = 10;
        let c_factorial = factorial_worker.get_factorial(c);

        // test
        let result = do_stop_early(&a_factorial, &b_factorial, &c_factorial, c);

        assert_eq!(result, false);
    }

    #[test]
    fn test_do_stop_early_yes() {
        let mut factorial_worker = FactorialWorker::new();
        let a_factorial = factorial_worker.get_factorial(4);
        let b_factorial = factorial_worker.get_factorial(5);
        let c: u64 = 6;
        let c_factorial = factorial_worker.get_factorial(c);

        // test
        let result = do_stop_early(&a_factorial, &b_factorial, &c_factorial, c);

        assert_eq!(result, true);
    }

    #[test]
    fn test_do_stop_early_overflow_check() {
        let mut factorial_worker = FactorialWorker::new();
        let a_factorial = factorial_worker.get_factorial(9);
        let b_factorial = factorial_worker.get_factorial(11);
        let c: u64 = 14;
        let c_factorial = factorial_worker.get_factorial(c);

        // test
        let result = do_stop_early(&a_factorial, &b_factorial, &c_factorial, c);

        assert_eq!(result, false);
    }

    #[test]
    fn test_compute_factorization() {
        let factorization_worker = FactorizationWorker::new();
        let n = 120;

        // test
        let result = factorization_worker.compute_factorization(n);

        assert_eq!(result[2], 3);
        assert_eq!(result[3], 1);
        assert_eq!(result[5], 1);
    }

    #[test]
    fn test_get_factorization() {
        let mut factorization_worker = FactorizationWorker::new();
        let n = 120;

        factorization_worker.get_factorization(n);

        // test
        let result = factorization_worker.get_factorization(n);

        assert_eq!(result[2], 3);
        assert_eq!(result[3], 1);
        assert_eq!(result[5], 1);
    }

    #[test]
    fn test_compute_factorial() {
        let factorial_worker = FactorialWorker::new();
        let n = 5;

        // test
        let result = factorial_worker.compute_factorial(n);

        assert_eq!(result[2], 3);
        assert_eq!(result[3], 1);
        assert_eq!(result[5], 1);
    }

    #[test]
    fn test_get_factorial() {
        let mut factorial_worker = FactorialWorker::new();
        let n = 5;

        factorial_worker.get_factorial(n);

        // test
        let result = factorial_worker.get_factorial(n);

        assert_eq!(result[2], 3);
        assert_eq!(result[3], 1);
        assert_eq!(result[5], 1);
    }
}
