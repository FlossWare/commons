# Changelog

All notable changes to the FlossWare Commons library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.9] - 2024-09-03

### Added
- Comprehensive test suite with 104 unit tests
  - `StringUtilTest` - 26 tests for string operations
  - `ArrayUtilTest` - 10 tests for array validation
  - `ObjectUtilTest` - 4 tests for object utilities
  - `ClassUtilTest` - 7 tests for class utilities
  - `FileUtilTest` - 12 tests for file operations
  - `SoapUtilTest` - 17 tests for SOAP utilities
  - `LoggerUtilTest` - 9 tests for logging
  - `UrlUtilTest` - 10 tests for URL operations
  - `AbstractBaseTest` - 9 tests for base class
- Security warnings in JavaDoc for serialization methods
- Input validation to all `SoapUtil` methods with proper null checks
- Comprehensive README with usage examples and architecture diagram
- CHANGELOG.md for version tracking

### Changed
- **BREAKING**: Serialization methods now throw exceptions instead of returning null
  - `StringUtil.toString()` throws `RuntimeException` on failure
  - `StringUtil.fromString()` throws `RuntimeException` on failure
  - `StringUtil.toCompressedString()` throws `RuntimeException` on failure
  - `StringUtil.fromCompressedString()` throws `RuntimeException` on failure
- Fixed logger initialization in `StringUtil` (was logging to wrong class)
- Improved `generateUniqueString()` to use UUID instead of `System.currentTimeMillis()`
- Serialization now uses Base64 encoding for proper binary-to-string conversion
- `ensureString()` methods now deprecated in favor of `requireNonBlank()`
- Enhanced exception messages throughout for better debugging

### Fixed
- Logger in `StringUtil` now correctly references `StringUtil.class` instead of `StringUtils.class`
- Binary serialization encoding issues (now uses Base64)
- Missing input validation in `SoapUtil.setUrl()`, `setHeader()`, `setHeaders()`, and `computeQName()`

### Security
- Added prominent warnings about Java deserialization risks in JavaDoc
- Documented that serialization methods are for internal/trusted use only
- Improved validation to prevent null pointer exceptions

## [1.8] - 2024-09-03

### Changed
- Updated dependencies to latest versions
- Apache Commons Lang3 updated to 3.17.0
- Apache CXF updated to 4.0.4

## [1.7] - 2024-09-03

### Changed
- Dependency updates via automated workflow

## Older Versions

Previous versions (1.0 - 1.6) were internal releases with various utility additions and dependency updates.

---

## Upgrade Guide

### Upgrading from 1.8 to 1.9

#### Breaking Changes

**Serialization Methods Now Throw Exceptions**

Previously, methods returned `null` on failure:
```java
// Old behavior (1.8)
String serialized = StringUtil.toString(obj);
if (serialized == null) {
    // Handle error
}
```

Now they throw exceptions:
```java
// New behavior (1.9)
try {
    String serialized = StringUtil.toString(obj);
} catch (IllegalArgumentException | RuntimeException e) {
    // Handle error
}
```

**Impact**: If you were checking for `null` returns, update to use try-catch blocks.

#### Deprecations

- `StringUtil.ensureString()` → Use `StringUtil.requireNonBlank()` instead

```java
// Deprecated
String value = StringUtil.ensureString(input);

// Recommended
String value = StringUtil.requireNonBlank(input);
```

#### Improvements You Get

- ✅ Better error messages when validation fails
- ✅ UUID-based unique strings (truly unique under concurrency)
- ✅ Proper Base64 encoding for serialization
- ✅ Comprehensive test coverage ensures stability
- ✅ All `SoapUtil` methods now validate inputs

No code changes required unless you:
1. Catch `null` from serialization methods (now throws exceptions)
2. Use deprecated `ensureString()` (still works, but deprecated)
