function queryUpdated() {
  var query1 = document.getElementById("query1").value;
  var query2 = document.getElementById("query2").value;
  d3.queue()
      .defer(d3.json,
        "http://localhost:5000/search?query=SELECT ?subject ?predicate1 ?predicate2 WHERE { ?subject " + query1 + " ?predicate1. ?subject " + query2 + " ?predicate2 . } ORDER BY DESC(?predicate1)")
      .await(makeGraphs)
}

var dataTable = dc.dataTable("#data-table");
var scatterPlot = dc.scatterPlot("#scatter");

function makeGraphs(error, data) {
  // document.getElementById("data").value = data
  data = formatData(data);
  console.log(data)
  dd = data
  ndx = crossfilter(data);
  countries = ndx.dimension(function(d) { return d.subject; });
  scatter = ndx.dimension(function(d) { return [d.object1, d.object2]; });
  scatterGroup = scatter.group();

  dataTable
    .dimension(countries)
    .group(function(d) {
      return "Number of Countries: " + countries.top(Infinity).length;
    })
    .columns([
      {
        label: "Country",
        format: function(d) { return d.subject; }
      },
      {
        label: "Predicate 1",
        format: function(d) { return numberWithCommas(d.object1); }
      },
      {
        label: "Predicate 2",
        format: function(d) { return numberWithCommas(d.object2); }
      }
    ])
    .size(Infinity)
    .sortBy(function(d) { return d.object1; })
    .order(d3.descending);

  scatterPlot
    .margins({ top: 24, left: 50, bottom: 45, right: 20 })
    .width(1100)
    .height(500)
    .x(d3.scale.log().domain([0, 20]))
    .y(d3.scale.log().domain([0, 20]))
    .yAxisLabel("Log(Predicate 2)")
    .xAxisLabel("Log(Predicate 1)")
    .clipPadding(20)
    .elasticY(true)
    .elasticX(true)
    .dimension(scatter)
    .excludedOpacity(0.5)
    .group(scatterGroup);

  dc.renderAll();

}

function numberWithCommas(x) {
    var parts = x.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
}

function numberWithCommas(x) {
    var parts = x.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
}

function formatData(data){
  var dd = []
  data.results.bindings.forEach(function(d) {
    var object1 = +d.predicate1.value
    var object2 = +d.predicate2.value
    if(object1 === 0)
      object1 = 0.01
    if(object2 === 0)
      object2 = 0.01
    dd.push({subject: d.subject.value, object1: +object1, object2: +object2})
  })
  return dd
}