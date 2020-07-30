
use std::thread;
use std::thread::JoinHandle;

mod factorial;

use factorial::util::t_log;
use factorial::find_factors;

fn get_chunk(index: u64, chunk_size: u64, max: u64) -> Option<(u64, u64)> {
    let mut result = None;
    let low = chunk_size * (index - 1);
    let high = chunk_size * index;
    if high <= max {
        result = Some((low, high))
    } else if low >= max {
        // no-op
    } else {
       result = Some((low, max))
    }
    result
}

fn main() {
    const MAX: u64 = 300;
    const CHUNK: u64 = 50;
    let mut done = false;
    let mut index: u64 = 1;
    let mut handles: Vec<JoinHandle<_>> = vec![];

    while ! done {
        let chunk = get_chunk(index, CHUNK, MAX);
        if let Some((low, high)) = chunk {
            let handle = thread::spawn(move || {
                t_log(&format!("TRACER {} {}", low, high));
                find_factors(low, high);
             });
            handles.push(handle);
            index += 1;
        } else {
            done = true;
        }
    }

    t_log("waiting in main...");
    for handle in handles.into_iter() {
        handle.join().unwrap();
    }

    t_log("Ready.");
}

#[allow(unused_imports)]
mod tests {
    use super::*;

    #[test]
    fn test_get_chunk_low_boundary() {
        let index: u64 = 1;
        let chunk: u64 = 10;
        let max: u64 = 25;

        // test
        let (low, high) = get_chunk(index, chunk, max).unwrap();

        assert_eq!(low, 0);
        assert_eq!(high, 10);
    }

    #[test]
    fn test_get_chunk_basic() {
        let index: u64 = 2;
        let chunk: u64 = 10;
        let max: u64 = 25;

        // test
        let (low, high) = get_chunk(index, chunk, max).unwrap();

        assert_eq!(low, 10);
        assert_eq!(high, 20);
    }

    #[test]
    fn test_get_chunk_high_boundary() {
        let index: u64 = 3;
        let chunk: u64 = 10;
        let max: u64 = 25;

        // test
        let (low, high) = get_chunk(index, chunk, max).unwrap();

        assert_eq!(low, 20);
        assert_eq!(high, 25);
    }

    #[test]
    fn test_get_chunk_out_of_range() {
        let index: u64 = 4;
        let chunk: u64 = 10;
        let max: u64 = 25;

        // test
        let result = get_chunk(index, chunk, max);

        assert_eq!(result.is_none(), true);
    }
}
