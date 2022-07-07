import React from 'react';
import {Navbar,Container,Offcanvas,Nav}  from 'react-bootstrap';
import {Link} from 'react-router-dom';

const AppNavBar = (props) => {
  return (
    <Navbar expand={false}>
      <Container fluid>
        <Navbar.Brand >{props.titulo}</Navbar.Brand>
        <Navbar.Toggle aria-controls="offcanvasNavbar" />
        <Navbar.Offcanvas
          id="offcanvasNavbar"
          aria-labelledby="offcanvasNavbarLabel"
          placement="end"
        >
          <Offcanvas.Header closeButton>
            <Offcanvas.Title id="offcanvasNavbarLabel">Gourmet</Offcanvas.Title>
          </Offcanvas.Header>
          <Offcanvas.Body>
            <Nav className="justify-content-end flex-grow-1 pe-3">
              <Link to = '/'>Home</Link>
              <Link to = '/recetarios'>Lista de recetarios</Link>
            </Nav>
          </Offcanvas.Body>
        </Navbar.Offcanvas>
      </Container>
    </Navbar>

  );
}

export default AppNavBar;
