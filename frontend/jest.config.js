module.exports = {
  preset: "@vue/cli-plugin-unit-jest/presets/typescript-and-babel",
  moduleNameMapper: {
    "^axios$": "<rootDir>/src/tests/__mocks__/axios.js",
    "^@/(.*)$": "<rootDir>/src/$1",
  },
  testMatch: ["**/src/tests/unit/**/*.spec.[jt]s?(x)"],
};
