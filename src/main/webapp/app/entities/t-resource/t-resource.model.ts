import { BaseEntity } from './../../shared';

export class TResource implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public description?: string,
        public category?: string,
        public color?: string,
        public material?: string,
        public unitPrice?: number,
        public length?: number,
        public width?: number,
        public height?: number,
        public weight?: number,
        public innerId?: number,
        public outerId?: number,
        public status?: string,
        public vendor?: string,
    ) {
    }
}
