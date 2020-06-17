

function linkCircle( sel, d ) {
    const radius = 75;

    sel.append("circle")
       .attr( "r", radius )
       .attr( "fill", "snow" ).attr( "stroke", "black" );

    sel.append( "a" )
       .attr( "xlink:href", d => d["URL"] )    
       .attr( "target", "_blank" ) 
       .append( "text" ).attr( "x", 0 ).attr( "y", 5 )                    
       .attr( "text-anchor", "middle" )        
       .attr( "font-family", "sans-serif" ).attr( "font-size", 14 )        
       .text( d => d["Desc"] );
}

function populateLinks() {                                         
    console.log(`TRACER ${new Date().toLocaleString()}`);

    const xOffset = 150, yOffset = 50;
    var x = 10;
    var y = 100;

    d3.csv( "links.csv" )                            
        .then( function( data ) {                              
            d3.select("svg")
              .selectAll("g")
              .data(data)
              .enter()
              .append("g")
              .attr("transform", function (d,i) {
                  x = x + xOffset;
                  y = y + yOffset;
                  return "translate(" + x + "," + y + ")"; 
              })
              .call(linkCircle);
        } );
}
