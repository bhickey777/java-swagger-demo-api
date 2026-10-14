
#!/usr/bin/env bash
set -euo pipefail

# Run from the Angular project's root directory.
cd "$(dirname "$0")"

OPENAPI_FILE="product-service-openapi.json"
OUTPUT_DIR="src/app/generated/product-api-client"

# Verify the OpenAPI definition exists.
if [[ ! -f "$OPENAPI_FILE" ]]; then
    echo "ERROR: $OPENAPI_FILE not found."
    exit 1
fi

echo "Generating Angular 21 Product API client..."

npx @openapitools/openapi-generator-cli generate \
    -i "$OPENAPI_FILE" \
    -g typescript-angular \
    -o "$OUTPUT_DIR" \
    --additional-properties=ngVersion=21.0.0,providedInRoot=true

echo "Angular Product API client generated successfully!"
echo "Output: $OUTPUT_DIR"

