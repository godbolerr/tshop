import { BaseEntity } from './../../shared';

export class TActivity implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public description?: string,
        public category?: string,
        public objective?: string,
        public concept?: string,
        public activityTime?: number,
        public totalTime?: number,
        public tags?: string,
        public status?: string,
    ) {
    }
}
