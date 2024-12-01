//endpoint insertar (POST): http://localhost:8080/api/v1/projects/insert
// JSON para insertar proyectos:

/*
{
  "projectId": 0,
  "projectName": "CRUD Productos",
  "decrip": "Usar cliente para consumir la api de productos",
  "fechaInicio": "2024-10-20",
  "fechaFin": "2024-11-17",
  "urlRepo": "https://www.github/Manuel",
  "urlDemo": "https://localhost:8080/api/productos",
  "picture": "https://www.google.es/productos",
  "statusdto": { "statusId": 3 },
  "developdto": [
    { "devId": 2 }
  ],
  "tecnodto": [
    { "techId": 1 }
  ]
} 

*/


//endpoint insertar (POST): http://localhost:8080/api/v1/developers/insert
//JSON para insertar Develop:

/*
 {
  "devId": 0,
  "devName": "Manuel",
  "devSurname": "Hernandez",
  "email": "manuel@gmail.com",
  "linkedin": "https://www.linkedin/Manuel",
  "github": "https://www.github/Manuel",
  "ProyectoEnDesarrollo": [
    { "projectId": 1 }
  ]
}
 */


//endpoint insertar (POST): http://localhost:8080/api/v1/tecnologies/insert
 //JSON para insertar Tecnology:

/*
 {
  "tecnoId": 0,
  "tecnoName": "react",
  "ProyectoUsandoTecnología": [
    { "projectId": 1 }
  ]
}
 */
