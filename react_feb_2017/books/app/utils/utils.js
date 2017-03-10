
export function searchData(target, data, index) {
    const lowerTarget = target.toLowerCase();

    const results = data.filter((row) => {
        const current = row[index].toString().toLowerCase();
        return (current.indexOf(target) > -1);
    });
    
    return results;
}

export function sortData(data, column, sortby, descending) {
    let results = Array.from(data);

    results.sort((a,b) =>  {
        let result = (a[column] > b[column] ? 1 : -1);
        if (descending) {
            result = (a[column] < b[column] ? 1 : -1);
        }
        return result;
    });

    return results;
}


