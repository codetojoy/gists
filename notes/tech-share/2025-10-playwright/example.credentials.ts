import { test, expect } from '@playwright/test';

test.describe('Login Form Acceptance Tests', () => {
  
  test('should successfully login with valid credentials from environment variables', async ({ page }) => {
    // Get credentials from environment variables
    const username = process.env.TEST_USERNAME;
    const password = process.env.TEST_PASSWORD;
    
    // Validate that credentials are provided
    if (!username || !password) {
      throw new Error('TEST_USERNAME and TEST_PASSWORD environment variables must be set');
    }
    
    // Navigate to the login page
    await page.goto('https://example.com/login');
    
    // Find the login form elements
    const emailInput = page.locator('input[type="email"], input[name="email"], input#email');
    const passwordInput = page.locator('input[type="password"], input[name="password"], input#password');
    const loginButton = page.locator('button[type="submit"], button:has-text("Login"), input[type="submit"]');
    
    // Verify form elements are visible
    await expect(emailInput).toBeVisible();
    await expect(passwordInput).toBeVisible();
    await expect(loginButton).toBeVisible();
    
    // Fill in the login credentials from environment variables
    await emailInput.fill(username);
    await passwordInput.fill(password);
    
    // Submit the form
    await loginButton.click();
    
    // Wait for navigation after login
    await page.waitForURL('**/dashboard', { timeout: 5000 });
    
    // Verify successful login
    await expect(page.locator('h1:has-text("Dashboard")')).toBeVisible();
    await expect(page.locator('[data-testid="user-menu"]')).toBeVisible();
  });
});
