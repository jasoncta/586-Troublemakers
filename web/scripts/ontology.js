function queryUpdated() {
  var query = document.getElementById("query").value;
  d3.queue()
      .defer(d3.json,
        "http://localhost:5000/search?query=SELECT ?subject ?predicate ?object WHERE {?subject " + query + " ?object .} ORDER BY DESC(?predicate)")
      .await(makeGraphs)
}

var chart = dc.barChart("#test")
  .width(1100)
  .height(400);

var dataTable = dc.dataTable("#data-table");

function makeGraphs(error, data) {
  // document.getElementById("data").value = data
  data = formatData(data);
  console.log(data)
  dd = data
  ndx = crossfilter(data);
  countries = ndx.dimension(function(d) { return d.subject; });
  gdp = countries.group().reduce(
    function(p, v) {
      p.gdp += v.object;
      return p;
    },
    function(p, v) {
      p.gdp -= v.object;
      return p;
    },
    function() {
      return { gdp: 0 };
    }
  );
  

  chart
    .margins({ top: 24, left: 130, bottom: 45, right: 0 })
    .dimension(countries)
    .group(gdp)
    .valueAccessor(function(d) { return +d.value.gdp; })
    .x(d3.scale.ordinal().domain([""]))
    .renderHorizontalGridLines(true)
    .renderVerticalGridLines(true)
    .brushOn(true)
    .clipPadding(15)
    .elasticY(true)
    .elasticX(true)
    .xUnits(dc.units.ordinal)
    .yAxisLabel("RDF Predicate")
    .xAxisLabel("Countries");

  chart.xAxis().tickValues([]);

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
        label: "Metric",
        format: function(d) { return numberWithCommas(d.object); }
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
    dd.push({subject: d.subject.value, object: +d.object.value})
  })
  return dd
}