

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

    d3.csv( "links.csv" )                            
        .then( function( data ) {                              

            var scX = d3.scaleLinear()
                        .domain( d3.extent(data, (d,i) => i) )
                        .range( [110, 910] );    
            var scY = d3.scaleLinear()
                        .domain( d3.extent(data, (d,i) => i) )
                        .range( [200, 400] );

            d3.select("svg")
              .selectAll("g")
              .data(data)
              .enter()
              .append("g")
              .attr("transform", 
                   (d,i) => "translate(" + scX(i) + "," + scY(i) + ")" )
              .call(linkCircle);
        } );
}
