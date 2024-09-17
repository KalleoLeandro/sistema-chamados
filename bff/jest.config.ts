import type { Config } from 'jest';

const config: Config = {
  preset: 'ts-jest',  // Usa ts-jest para suportar TypeScript
  testEnvironment: 'node', // Ambiente de teste
  collectCoverage: true,
  coverageDirectory: 'coverage',
  coverageReporters: ['html', 'lcov', 'text'],
  reporters: [
    'default',
    ['jest-sonar', {
      outputDirectory: 'coverage',
      outputName: 'sonar-report.xml',
      reportedFilePath: 'relative',
      relativeRootDir: './src',
    }]
  ]
};

export default config;
