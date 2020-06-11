import { WebPlugin } from '@capacitor/core';
import { CapacitorFileChooserPlugin } from './definitions';

export class CapacitorFileChooserWeb extends WebPlugin implements CapacitorFileChooserPlugin {
  constructor() {
    super({
      name: 'CapacitorFileChooser',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const CapacitorFileChooser = new CapacitorFileChooserWeb();

export { CapacitorFileChooser };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorFileChooser);
