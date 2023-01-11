<h1>TPCapgemini BACKEND</h1>
<h4>El TP consta de dos partes: backend y frontend</h4>
<h4><a href="https://github.com/AryDegtiar/TP-Capgemini-Angular" target=_blank> 📂 Link frontend: https://github.com/AryDegtiar/TP-Capgemini-Angular <a><h4>
En este archivo voy a mostrar la consigna y explicar mi desarrollo

<br>

<h2>📃 Enunciado: 📃</h2>
La plataforma permite a los gestores cargar productos base con posibles personalizaciones segun un area y tipo, para que los vendedores puedan personalizar con imágenes o frases, etc. Los vendedores pueden establecer el nombre y precio de la personalización, el cual se suma al precio base del producto para determinar el precio final, tambien los vendedores pueden pausar y cancelar las publicaciones. Los compradores pueden seleccionar los productos que desean comprar y luego realizar el pago a través de los medios de pago admitidos por el vendedor. Se debe implementar un carrito de compra, ademas tiene que existir un historial de compras. 

Los articulos cargados van a ser del mismo vendedor ya que como se aclaro antes, cada vendedor puede tener diferentes metodos de pago.

Se pide implementarlo en una arquitectura Web, con 2 componentes
- FrontEnd: proyecto Front usado el FW Angular2
- BackEnd: proyecto API REST con persistencia - usando Java-Spring

Se deben implementar las pantallas del comprador:
- Login
- Proceso de compra
- Listado de productos
- Agregar al carrito
- Confirmar Compra
- Mostrar resultado de Compra
- Ver el listado de las compras realizadas
- se deben desarrollar todos los métodos en la API REST para darle soporte a esto
<hr>
<br>

<h2>🤔 Logica: 🤔</h2>
<p align="center">
  <img src="https://user-images.githubusercontent.com/82188877/211683914-5ad89f0a-4a7c-408e-8724-5acf2342eda7.png">
</p>

#### Estructura:
Un producto base puede tener categorias, a su vez puede tener varias posibles personalizaciones, cada posible personalizacion tiene un area y un tipo. Cada tipo y area son creadas una unica vez ya que despues es combinada con la posible personalizacion destaco que hay una relacion de many to many de producto base a posible personalizacion ya que de esa manera no se repite la tabla.
La publicacion va a ser el producto final en el cual va a interactuar el comprador. Cada publicacion va a tener personalizaciones que estas si van necesitar ser creadas por cada publicacion ya que puede variar el contenido, la descripcion o el precio. 
Un vendedor puede tener muchos metedos de pago (spoiler los metodos de pago los cree con enum)
Por ultimo cada cliente tiene compras realizadas que van a ser el preducto con su cantidad.



<br>

<h3> Objetivos logrados: </h3>
<p>🦖  ABM Producto base </p>  
<p>🦖  ABM Publicaciones </p>
<p>🦖  ABM Categorias </p>
<p>🦖  ABM Usuarios </p>
<p>🦖  ABM Carrito de compras </p>
<p>🦖  ABM Historial de compras </p>
 
<h3> Tecnologias usadas: </h3>
🔓 JAVA - Spring Boot
🔓 MySQL - Hibernate
🔓 Angular - Boostrap


<h3> Nota: </h3>
El plazo de entrega para este tp fue de 1 mes, dictado dentro de la cursada, por dicha razon hay codigo que se puede mejorar ya que a medida que ibamos aprendiendo teniamos que ir haciendo el tp.
  
