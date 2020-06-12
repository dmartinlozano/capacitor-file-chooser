import "@capacitor/core";
declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFileChooser?: CapacitorFileChooser;
  }
}

export interface CapacitorFileChooser {
  picker(options: { action: string, startDirectory: string }): Promise<any>;
}
