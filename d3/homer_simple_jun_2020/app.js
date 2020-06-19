
window.addEventListener( "load", buildLinks );
function buildLinks() {
    console.log(`TRACER ${new Date().toLocaleString()}`);
  
    const dsv = d3.dsvFormat(",");

    d3.text("links.txt").then( function(res) {
        var data = dsv.parseRows( res, (d,i,cs) => {        
            var desc = d[0].trim();
            var link = (d[1] || "").trim();
            var isHeader = (link !== "");
            console.log(`TRACER desc: '${desc}' link: ${link}`);
            return { desc, link, isHeader };
        } );    

        d3.select("#links")
          .select("p")
          .data(data)
          .join( 
              function(enter) {
                console.log(`TRACER enter`);
                return enter.filter( d => d.isHeader )
                            .append('h2')
                            .text(d => d.desc);
              }
          );
    });
}
