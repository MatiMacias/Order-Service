<h1>Microservicio de Gestión de Órdenes</h1>

<p>Este proyecto es un microservicio backend responsable de la gestión de clientes, productos y órdenes.
Está diseñado siguiendo buenas prácticas profesionales y puede formar parte de una arquitectura de microservicios.</p>

<hr>

<h2>Descripción General</h2>

<p>El Microservicio de Gestión de Órdenes se encarga de:</p>

<ul>
  <li>Gestión de clientes (crear, actualizar, eliminar, listar)</li>
  <li>Gestión del catálogo de productos</li>
  <li>Creación y seguimiento de órdenes</li>
  <li>Gestión de ítems de orden con cantidad, precio unitario y subtotal</li>
  <li>Cálculo automático del total de la orden</li>
</ul>

<p>El servicio expone APIs REST y utiliza DTOs para garantizar un flujo de datos limpio entre las capas
y evitar la exposición directa de las entidades.</p>

<hr>

<h2>Arquitectura</h2>

<p>El proyecto sigue una arquitectura en capas:</p>

<ul>
  <li><strong>Capa Controller</strong>: Maneja las solicitudes y respuestas HTTP</li>
  <li><strong>Capa Service</strong>: Contiene la lógica de negocio</li>
  <li><strong>Capa Repository</strong>: Maneja la persistencia con Spring Data JPA</li>
  <li><strong>Capa DTO</strong>: Define los modelos de entrada y salida</li>
</ul>

<p>Las entidades están desacopladas de los contratos de la API mediante DTOs,
permitiendo mayor mantenibilidad y escalabilidad.</p>

<hr>

<h2>Modelo de Dominio</h2>

<h3>Customer</h3>
<ul>
  <li>id</li>
  <li>name</li>
  <li>email</li>
</ul>

<h3>Product</h3>
<ul>
  <li>id</li>
  <li>name</li>
  <li>price</li>
</ul>

<h3>Order</h3>
<ul>
  <li>id</li>
  <li>customer</li>
  <li>createdAt</li>
  <li>status</li>
  <li>total</li>
  <li>items</li>
</ul>

<h3>OrderItem</h3>
<ul>
  <li>id</li>
  <li>order</li>
  <li>product</li>
  <li>quantity</li>
  <li>unitPrice</li>
  <li>subtotal</li>
</ul>

<p>La relación entre Órdenes y Productos se implementa mediante una entidad intermedia (OrderItem),
permitiendo manejar correctamente cantidades y precios.</p>

<hr>

<h2>Tecnologías Utilizadas</h2>

<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>Spring Data JPA</li>
  <li>Hibernate</li>
  <li>MySQL</li>
  <li>Docker y Docker Compose</li>
  <li>Maven</li>
</ul>

<hr>

<h2>Base de Datos</h2>

<p>La base de datos se ejecuta dentro de un contenedor Docker utilizando MySQL.</p>

<p>El esquema es gestionado automáticamente a través de las entidades JPA.</p>

<hr>

<h2>Diseño de la API</h2>

<p>La API sigue los principios REST y separa claramente los datos de entrada y salida mediante DTOs:</p>

<ul>
  <li>Los requests contienen solo la información necesaria</li>
  <li>Los responses exponen únicamente los datos requeridos</li>
  <li>Las entidades nunca se exponen directamente</li>
</ul>

<hr>

<h2>Preparado para Microservicios</h2>

<p>Este proyecto está diseñado para funcionar como un microservicio independiente.</p>

<p>Puede integrarse fácilmente con:</p>

<ul>
  <li>Un microservicio de autenticación</li>
  <li>Un microservicio de pagos</li>
  <li>Un API Gateway o gateway en la nube</li>
</ul>

<p>Posibles mejoras futuras:</p>

<ul>
  <li>Autenticación JWT</li>
  <li>Comunicación entre microservicios</li>
  <li>Arquitectura orientada a eventos</li>
  <li>Despliegue en la nube</li>
</ul>

<hr>

<h2>Autor</h2>

<p>Desarrollado como proyecto de portfolio profesional enfocado en backend.</p>
