import { WebPlugin } from '@capacitor/core';
import { CapacitorFileChooserPlugin } from './definitions';
import { registerWebPlugin } from '@capacitor/core';

export class CapacitorFileChooserWeb extends WebPlugin implements CapacitorFileChooserPlugin {
  constructor() {
    super({
      name: 'CapacitorFileChooser',
      platforms: ['android']
    });
  }

  async picker(options: { action: string, startDirectory: string }): Promise<any> {
    console.debug('picker with options: ', options);
    return Promise.resolve();
  }
}

const CapacitorFileChooser = new CapacitorFileChooserWeb();
export { CapacitorFileChooser };
registerWebPlugin(CapacitorFileChooser);
