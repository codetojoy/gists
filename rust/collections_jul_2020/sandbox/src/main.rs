
fn find_nearest(list: &Vec<u32>, target: u32) -> u32 {
    let (nearest, _tmp) = list.into_iter().fold((0, 100), |acc, x| -> (u32, u32) {
        let (card, distance) = acc;
        // let this_diff = *x as i32 - target as i32;
        let this_distance = (*x as i32 - target as i32).abs() as u32;
        println!("TRACER x {} card {} distance {} this_distance {}", x, card, distance, this_distance);
        if this_distance < distance  {
            (*x, this_distance)
        } else {
            acc
        }
    });
    nearest
}

fn go() {
    let list: Vec<u32> = vec![10,20,50,40,30];
    // let min = list.into_iter().min().unwrap();
    // println!("TRACER min value: {}", min);
    let nearest = find_nearest(&list, 33);
    println!("TRACER nearest value: {}", nearest);
}

fn main() {
    go();
    println!("Ready.");
}
