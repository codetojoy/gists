import { test, expect } from '@playwright/test';

test('API test: Login and fetch items with session', async ({ request }) => {
  const baseURL = 'https://api.example.com';
  
  // Step 1: Login and capture session ID
  console.log('Step 1: Logging in...');
  
  const loginResponse = await request.post(`${baseURL}/login`, {
    headers: {
      'Content-Type': 'application/json'
    },
    data: {
      username: 'testuser',
      password: 'testpass'
    }
  });
  
  // Verify login response
  expect(loginResponse.status()).toBe(200);
  
  const loginBody = await loginResponse.json();
  console.log('Login response:', loginBody);
  
  // Extract and verify session ID
  expect(loginBody.session_id).toBeTruthy();
  const sessionId = loginBody.session_id;
  console.log(`Session ID obtained: ${sessionId}`);
  
  // Step 2: Use session ID to get items
  console.log('\nStep 2: Fetching items with session...');
  
  const itemsResponse = await request.get(`${baseURL}/get-items`, {
    headers: {
      'Content-Type': 'application/json',
      'Cookie': `session-id=${sessionId}`
    },
    params: {
      limit: '10'
    }
  });
  
  // Verify items response
  expect(itemsResponse.status()).toBe(200);
  
  const itemsBody = await itemsResponse.json();
  console.log('Items response:', itemsBody);
  
  // Verify items structure and content
  expect(itemsBody.items).toBeTruthy();
  expect(itemsBody.items.length).toBeGreaterThan(0);
  
  console.log('\nTest completed successfully!');
});
