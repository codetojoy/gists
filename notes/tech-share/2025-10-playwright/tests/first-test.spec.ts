
import { test } from '@playwright/test';

test('my first test', async ({page}) => {
  await page.goto('http://localhost:4200');

  await page.getByText('Forms').click();
});