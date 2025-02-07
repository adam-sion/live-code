// vite.config.js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  define: {
    // Define `global` to avoid errors related to missing global object in the browser
    global: 'window', // Make `global` point to `window` in the browser
  },
});
