d3.xhr2 = function(url, method, params, mime, callback) {
   var req = new XMLHttpRequest;
   if (arguments.length < 5) callback = mime, mime = null;
   else if (mime && req.overrideMimeType) req.overrideMimeType(mime);
   req.open(method, url, true);
   if (mime) req.setRequestHeader("Accept", mime);
   req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   req.setRequestHeader("Content-length", params.length);
   req.setRequestHeader("Connection", "close");
   req.onreadystatechange = function() {
     if (req.readyState === 4) {
       var s = req.status;
       callback(s >= 200 && s < 300 || s === 304 ? req : null);
     }
   };
   req.send(params);
 };

 function ActualizarProfesor() {
   var id = d3.select("#id").property("value");
   var cedula = d3.select("#cedula").property("value");
   var nombre = d3.select("#nombre").property("value");
   var titulo = d3.select("#titulo").property("value");
   var area = d3.select("#area").property("value");
   var telefono = d3.select("#telefono").property("value");
   var correo = d3.select("#correo").property("value");
   var params = "id="+id+"&cedula="+cedula+"&nombre="+nombre+
                "&titulo="+titulo+"&area="+area+"&correo="+correo+
                "&telefono="+telefono;
   d3.xhr2("http://localhost:8089/rest/profesores/"+id,"PUT",params,function(json) {
   });
   ListadoProfesores();
 }