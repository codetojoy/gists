

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

    // TODO: ugh ... there must be a better way 
    const numRows = 7;

    var scX = d3.scaleLinear().domain( [0, numRows-1] ).range( [110, 910] );    
    var scY = d3.scaleLinear().domain( [0, numRows-1] ).range( [200, 400] );

    d3.csv( "links.csv" )                            
        .then( function( data ) {                              
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
