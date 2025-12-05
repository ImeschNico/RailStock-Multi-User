import React from "react";
import { Navigation } from "./navigation";
import { Outlet } from "react-router-dom";

export const Layout = () => {
  return (
    <div className="app">
      <header className="app-header">
        <Navigation />
        <h1 className="app-title">RailStock</h1>
      </header>

      <main className="layout-main-content">
        <div className="page-container">
          <Outlet />
        </div>
      </main>

      <footer className="layout-footer">
        <p>© RailStock. All rights reserved</p>
        <p>Made with ❤️ by Nico Imesch</p>
      </footer>
    </div>
  );
};
