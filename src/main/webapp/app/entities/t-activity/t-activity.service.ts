import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { TActivity } from './t-activity.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TActivityService {

    private resourceUrl = 'api/t-activities';

    constructor(private http: Http) { }

    create(tActivity: TActivity): Observable<TActivity> {
        const copy = this.convert(tActivity);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(tActivity: TActivity): Observable<TActivity> {
        const copy = this.convert(tActivity);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: string): Observable<TActivity> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(tActivity: TActivity): TActivity {
        const copy: TActivity = Object.assign({}, tActivity);
        return copy;
    }
}
