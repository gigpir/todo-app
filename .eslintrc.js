module.exports = {
  env: {
    node: true,
    es2021: true,
    mocha: true
  },
  extends: [
    'airbnb-base',
    'prettier',
    'eslint:recommended'
  ],
  parserOptions: {
    ecmaVersion: 12,
    sourceType: 'module',
  },
  plugins: ['prettier'],
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
    "no-unused-vars": process.env.NODE_ENV === "production" ? "error" : "off",
    "camelcase": "off",
    "import/no-dynamic-require": "off",
    "no-param-reassign": "off",
    "no-shadow": "off",
    "prettier/prettier": ["error", {"singleQuote": true, "parser": "flow", "semi": true}]
  }
};
