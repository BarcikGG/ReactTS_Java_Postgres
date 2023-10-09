/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        'gtr': "url('/src/assets/bg_GTR.png')",
      },
    },
  },
  plugins: [],
}

