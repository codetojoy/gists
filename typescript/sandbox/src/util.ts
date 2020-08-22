
export class MyRandom {
    getRandomInt(n) {
        let min = Math.ceil(1);
        let max = Math.floor(n);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
