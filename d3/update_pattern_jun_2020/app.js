
const radius = 20;
const transitionDurationInMillis = 2500;

window.addEventListener( "load", makeUpdate1 );
function makeUpdate1() {
    var ds1 = [ [2, 3, "green"], [1, 2, "red"], [2, 1, "blue"],   
                [3, 2, "yellow"] ];    
    var ds2 = [ [1, 1, "red"], [3, 3, "black"], [1, 3, "lime"],
                [3, 1, "blue"]];
    
    var scX = d3.scaleLinear().domain([1, 3]).range([100, 400]),  
        scY = d3.scaleLinear().domain([1, 3]).range([50, 100]);
    
    var svg = d3.select( "#update1" );                             
    
    svg.on( "click", function() {                                 
        [ ds1, ds2 ] = [ ds2, ds1 ];                              
        
        var cs = svg.selectAll( "circle" ).data( ds1, d=>d[2] );  
        
        cs.exit()
          .transition()
          .duration(transitionDurationInMillis)
          .attr('r', 0)
          .style('opacity', 0)
          .attr('cx', 1000)
          .on('end', function() {
            d3.select(this).remove();
          });
        
        cs = cs.enter().append( "circle" )                        
            .attr( "r", radius ).attr( "fill", d=>d[2] )
            .merge( cs );                                        

        cs.attr( "cx", d=>scX(d[0]) ).attr( "cy", d=>scY(d[1]) ); 
    } );
    
    svg.dispatch( "click" );                                     
}

window.addEventListener( "load", makeUpdate2 );
function makeUpdate2() {
    var dsA = [ [2, 3, "green"], [1, 2, "red"], [2, 1, "blue"], [3, 2, "yellow"] ];  
    var dsB = [ [1, 1, "red"], [3, 3, "black"], [1, 3, "lime"], [3, 1, "blue"]];
    
    var scX = d3.scaleLinear().domain([1, 3]).range([100, 400]),  
        scY = d3.scaleLinear().domain([1, 3]).range([50, 100]);
    
    var svg = d3.select( "#update2" );                             
    
    svg.on( "click", function() {                                 
        [ dsA, dsB ] = [ dsB, dsA ];                              
        
        var cs = svg.selectAll( "circle" ).data( dsA, d=>d[2] );  

        cs.join(
            function(enter) {
              console.log(`TRACER enter`);
              return enter.append( "circle" )                        
                          .attr( "r", radius ).attr( "fill", d=>d[2] );
            },
            function(update) {
              console.log(`TRACER update`);
              return update; 
            },
            function(exit) {
              console.log(`TRACER exit`);
              return exit.transition()
                         .duration(transitionDurationInMillis)
                         .attr('r', 0)
                         .style('opacity', 0)
                         .attr('cx', 1000)
                         .on('end', function() {
                           d3.select(this).remove();
                         });
            }
        ).attr( "cx", d=>scX(d[0]) ).attr( "cy", d=>scY(d[1]) ); 
    } );
    
    svg.dispatch( "click" );                                     
}
