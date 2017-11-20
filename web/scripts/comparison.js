function queryUpdated() {
  var query1 = document.getElementById("query1").value;
  var query2 = document.getElementById("query2").value;
  d3.queue()
      .defer(d3.json,
        "http://localhost:5000/search?query=SELECT ?subject ?predicate1 ?predicate2 WHERE { ?subject " + query1 + " ?predicate1. ?subject " + query2 + " ?predicate2 . } ORDER BY DESC(?predicate1)")
      .await(makeGraphs)
}

var dataTable = dc.dataTable("#data-table");

function makeGraphs(error, data) {
  // document.getElementById("data").value = data
  data = formatData(data);
  console.log(data)
  dd = data
  ndx = crossfilter(data);
  countries = ndx.dimension(function(d) { return d.subject; });

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
        format: function(d) { return d.object1; }
      },
      {
        label: "Predicate 2",
        format: function(d) { return d.object2; }
      }
    ])
    .size(Infinity)
    .sortBy(function(d) { return d.object; })
    .order(d3.descending);

  dc.renderAll();

}

function numberWithCommas(x) {
    var parts = x.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
}

function formatData(data){
  var dd = []
  data.results.bindings.forEach(function(d) {
    dd.push({subject: d.subject.value, object1: +d.predicate1.value, object2: +d.predicate2.value})
  })
  return dd
}