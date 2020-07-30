
mod factorial;

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
    const MAX: u64 = 121;
    const CHUNK: u64 = 10;
    let mut done = false;
    let mut index: u64 = 1;

    while ! done {
        let chunk = get_chunk(index, CHUNK, MAX);
        if let Some((low, high)) = chunk {
            println!("TRACER {} {}", low, high);
            find_factors(low, high);
            index += 1;
        } else {
            done = true;
        }
    }

    println!("Ready.");
}
