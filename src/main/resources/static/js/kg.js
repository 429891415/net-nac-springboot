function startKG(links) {

    var nodes = {};

    links.forEach(function(link)
    {
        link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
        link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
    });

    var width = 1154,
        height = 845;

    var force = d3.layout.force()
        .nodes(d3.values(nodes))
        .links(links)
        .size([width, height])
        .linkDistance(250)
        .charge(-1500)
        .on("tick", tick)
        .start();

    var svg = d3.select("#container").append("svg")
        .attr("width", "100%")
        .attr("height", "100%")
        .attr("viewBox", "0 0 "+width+" "+height)

    var marker=
        svg.append("marker")
            .attr("id", "resolved")
            .attr("markerUnits","userSpaceOnUse")
            .attr("viewBox", "0 -5 10 10")
            .attr("refX",32)
            .attr("refY", -1)
            .attr("markerWidth", 12)
            .attr("markerHeight", 12)
            .attr("orient", "auto")
            .attr("stroke-width",2)
            .append("path")
            .attr("d", "M0,-5L10,0L0,5")
            .attr('fill','#000000');

    var edges_line = svg.selectAll(".edgepath")
        .data(force.links())
        .enter()
        .append("path")
        .attr({
            'd': function(d) {return 'M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y},
            'class':'edgepath',
            'id':function(d,i) {return 'edgepath'+i;}})
        .style("stroke",function(d){
            var lineColor;
            lineColor="#B43232";
            return lineColor;
        })
        .style("pointer-events", "none")
        .style("stroke-width",0.5)
        .attr("marker-end", "url(#resolved)" );

    var edges_text = svg.append("g").selectAll(".edgelabel")
        .data(force.links())
        .enter()
        .append("text")
        .style("pointer-events", "none")
        .attr({  'class':'edgelabel',
            'id':function(d,i){return 'edgepath'+i;},
            'dx':80,
            'dy':0
        });

    edges_text.append('textPath')
        .attr('xlink:href',function(d,i) {return '#edgepath'+i})
        .style("pointer-events", "none")
        .text(function(d){return d.rela;});

    var circle = svg.append("g").selectAll("circle")
        .data(force.nodes())
        .enter().append("circle")
        .style("fill",function(node){
            var color;
            var link=links[node.index];
            color="#F9EBF9";
            return color;
        })
        .style('stroke',function(node){
            var color;
            var link=links[node.index];
            color="#A254A2";
            return color;
        })
        .attr("r", 28)
        .on("click",function(node)
        {
            edges_line.style("stroke-width",function(line){
                console.log(line);
                if(line.source.name==node.name || line.target.name==node.name){
                    return 4;
                }else{
                    return 0.5;
                }
            });
        })
        .call(force.drag);

    var text = svg.append("g").selectAll("text")
        .data(force.nodes())
        .enter()
        .append("text")
        .attr("dy", ".35em")
        .attr("text-anchor", "middle")
        .style('fill',function(node){
            var color;
            var link=links[node.index];
            color="#A254A2";
            return color;
        }).attr('x',function(d){
            var re_en = /[a-zA-Z]+/g;
            if(d.name.match(re_en)){
                d3.select(this).append('tspan')
                    .attr('x',0)
                    .attr('y',2)
                    .text(function(){return d.name;});
            }

            else if(d.name.length<=15){
                d3.select(this).append('tspan')
                    .attr('x',0)
                    .attr('y',2)
                    .text(function(){return d.name;});
            }else{
                var top=d.name.substring(0,4);
                var bot=d.name.substring(4,d.name.length);

                d3.select(this).text(function(){return '';});

                d3.select(this).append('tspan')
                    .attr('x',0)
                    .attr('y',-7)
                    .text(function(){return top;});

                d3.select(this).append('tspan')
                    .attr('x',0)
                    .attr('y',10)
                    .text(function(){return bot;});
            }
        });

    function tick() {
        circle.attr("transform", transform1);
        text.attr("transform", transform2);

        edges_line.attr('d', function(d) {
            var path='M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y;
            return path;
        });

        edges_text.attr('transform',function(d,i){
            return 'rotate(0)';
        });
    }

    function linkArc(d) {
        return 'M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y
    }

    function transform1(d) {
        return "translate(" + d.x + "," + d.y + ")";
    }
    function transform2(d) {
        return "translate(" + (d.x) + "," + d.y + ")";
    }
}