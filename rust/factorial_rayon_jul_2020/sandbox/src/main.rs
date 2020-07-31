
use rayon::prelude::*;
use std::vec::Vec;
use std::sync::{Arc, Mutex};

mod factorial;

use factorial::{Chunk, FactorialWorker, find_factors};
use factorial::util::t_log;

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

fn get_chunks(chunk_size: u64, max: u64) -> Vec<Chunk> {
    let mut chunks: Vec<Chunk> = vec![];
    let mut done = false;
    let mut index: u64 = 1;

    while ! done {
        let tuple = get_chunk(index, chunk_size, max);
        if let Some((low, high)) = tuple {
            let chunk = Chunk{low: low, high: high};
            chunks.push(chunk);
            index += 1;
        } else {
            done = true;
        }
    }

    chunks
}

// NOTE: the mutex coverage is probably too coarse, in terms of granularity
// TODO: re-design this for better throughput 
fn main() {
    const MAX: u64 = 200;
    const CHUNK_SIZE: u64 = 50;
    let chunks: Vec<Chunk> = get_chunks(CHUNK_SIZE, MAX);
    let factorial_worker = Arc::new(Mutex::new(FactorialWorker::new()));

    chunks.into_par_iter().for_each(move |chunk| find_factors(&chunk, &factorial_worker) );

    t_log("Ready.");
}

#[allow(unused_imports)]
mod tests {
    use super::*;

    #[test]
    fn test_get_chunks() {
        let chunk: u64 = 10;
        let max: u64 = 25;

        // test
        let chunks = get_chunks(chunk, max);

        assert_eq!(chunks.len(), 3);
    }

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
